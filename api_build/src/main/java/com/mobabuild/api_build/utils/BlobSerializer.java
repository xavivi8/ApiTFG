package com.mobabuild.api_build.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Blob;

public class BlobSerializer extends JsonSerializer<Blob> {

    @Override
    public void serialize(Blob blob, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        byte[] bytes = BlobUtils.blobToBytes(blob);
        jsonGenerator.writeBinary(bytes);
    }
}