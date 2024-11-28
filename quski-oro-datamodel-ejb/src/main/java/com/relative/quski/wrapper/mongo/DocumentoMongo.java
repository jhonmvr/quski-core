package com.relative.quski.wrapper.mongo;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
@Data
@Builder
public class DocumentoMongo  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String type;
    private String process;
    private long relatedId;
    private String relatedIdStr;
    private int typeAction;
    private String fileBase64;
    private String objectId;
}
