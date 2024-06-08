package com.mobabuild.api_build.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobDeserializer extends JsonDeserializer<Blob> {

    @Override
    public Blob deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        byte[] bytes = jsonParser.getBinaryValue();
        try {
            return new SerialBlob(bytes);
        } catch (SQLException e) {
            throw new IOException("Error deserializing blob", e);
        }
    }
}