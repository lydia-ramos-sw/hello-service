package com.smallworldfs.helloservice.service;

import static com.smallworldfs.helloservice.error.ItemIssue.CANNOT_CREATE;
import static com.smallworldfs.helloservice.error.ItemIssue.FATAL_ITEM;
import static com.smallworldfs.helloservice.error.ItemIssue.ITEM_FORBIDDEN;
import static com.smallworldfs.helloservice.error.ItemIssue.ITEM_NOT_FOUND;
import static com.smallworldfs.helloservice.error.ItemIssue.OPERATION_NOT_ALLOWED;

import com.smallworldfs.helloservice.configuration.HelloProperties;
import com.smallworldfs.helloservice.db.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelloService {

    private static final String OP_CREATE = "CREATE";
    private static final String OP_GET = "GET";
    private static final String OP_UPDATE = "UPDATE";
    private final HelloProperties props;

    public Item getItem(Integer itemNumber, String grant) {
        Item item = new Item(grant, itemNumber);
        validate(item, OP_GET);
        return item;
    }

    public Item createItem(Item item) {
        validate(item, OP_CREATE);
        return item;
    }

    public Item updateItem(Item item) {
        validate(item, OP_UPDATE);
        return new Item();
    }

    private void validate(Item item, String operation) {
        if (!isGrantEnoughForOperation(item.getGrantLevel(), operation)) {
            throw OPERATION_NOT_ALLOWED.withParameters(operation, item.getGrantLevel()).asException();
        }
        if (!isResourceAllowedForGrant(item.getItem(), item.getGrantLevel())) {
            throw ITEM_FORBIDDEN.withParameters(item.getItem()).asException();
        }
        if (!isItemAvailable(item.getItem())) {
            throw ITEM_NOT_FOUND.withParameters(item.getItem()).asException();
        }
        if (isItemAfatalItem(item.getItem())) {
            throw FATAL_ITEM.withParameters(item.getItem()).asException();
        }
        if (operation.equals(OP_CREATE)) {
            validateCreate(item);
        }
    }

    private void validateCreate(Item item) {
        if (!isItemNonCreatable(item.getItem())) {
            throw CANNOT_CREATE.withParameters(item.getItem()).asException();
        }
    }

    private boolean isGrantEnoughForOperation(String grant, String operation) {
        if (operation.equals(OP_GET)) {
            return props.getGrantsOperationGet()
                    .stream()
                    .anyMatch(x -> grant.equals(x));
        }
        if (operation.equals(OP_CREATE)) {
            return props.getGrantsOperationCreate()
                    .stream()
                    .anyMatch(x -> grant.equals(x));
        }
        if (operation.equals(OP_UPDATE)) {
            return props.getGrantsOperationUpdate()
                    .stream()
                    .anyMatch(x -> grant.equals(x));
        }
        return false;
    }

    private boolean isResourceAllowedForGrant(Integer item, String grant) {
        return !props.getItemsHigherGrants()
                .stream()
                .anyMatch(x -> item.equals(x)) || grant.equals("A");
    }

    private boolean isItemAvailable(Integer item) {
        return !props.getItemsNotAvailable()
                .stream()
                .anyMatch(x -> item.equals(x));
    }

    private boolean isItemAfatalItem(Integer item) {
        return props.getItemsFatal()
                .stream()
                .anyMatch(x -> item.equals(x));
    }

    private boolean isItemNonCreatable(Integer item) {
        return !props.getItemsCanNotBeCreated()
                .stream()
                .anyMatch(x -> item.equals(x));
    }
}
