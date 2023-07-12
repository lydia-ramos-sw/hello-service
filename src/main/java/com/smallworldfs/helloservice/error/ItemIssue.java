package com.smallworldfs.helloservice.error;

import com.smallworldfs.error.issue.DefaultIssueType;
import com.smallworldfs.error.issue.Issue;
import com.smallworldfs.error.issue.IssueType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemIssue implements Issue {

    ITEM_NOT_FOUND("Item '{0}' could not be found.", DefaultIssueType.NOT_FOUND),
    OPERATION_NOT_ALLOWED("Operation '{0}' is not available for grant {1}.", DefaultIssueType.AUTHORIZATION),
    ITEM_FORBIDDEN("Item '{0}' is not available to you.", DefaultIssueType.AUTHORIZATION),
    FATAL_ITEM("Item '{0}' causes fatal error.", DefaultIssueType.INTERNAL_ERROR),
    CANNOT_CREATE("Item '{0}' could not be created.", ExtendedIssueType.CONFLICT);

    private final String messageTemplate;
    private final IssueType type;
}
