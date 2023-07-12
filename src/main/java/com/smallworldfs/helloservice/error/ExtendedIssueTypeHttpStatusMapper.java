package com.smallworldfs.helloservice.error;

import com.smallworldfs.starter.service.error.status.DefaultIssueTypeHttpStatusMapper;
import org.springframework.http.HttpStatus;

public class ExtendedIssueTypeHttpStatusMapper extends DefaultIssueTypeHttpStatusMapper {

    public ExtendedIssueTypeHttpStatusMapper() {
        this.addMapping(ExtendedIssueType.CONFLICT, HttpStatus.CONFLICT);
    }
}
