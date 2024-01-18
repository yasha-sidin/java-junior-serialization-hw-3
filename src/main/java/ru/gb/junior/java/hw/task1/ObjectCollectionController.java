package ru.gb.junior.java.hw.task1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.gb.junior.java.task3.ToDoV2;

import java.io.*;
import java.util.List;

public class ObjectCollectionController<T extends Serializable> {
    public final String FILE_JSON;
    public final String FILE_BIN;
    public final String FILE_XML;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public ObjectCollectionController(String jsonFile, String binFile, String xmlFile) {
        if (!jsonFile.endsWith(".json") && binFile.endsWith(".bin") && xmlFile.endsWith(".xml")) {
            throw new RuntimeException("Wrong format with one of file.");
        }
        FILE_JSON = jsonFile;
        FILE_BIN = binFile;
        FILE_XML = xmlFile;
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public List<T> loadFromJson(Class<T> clazz) {
        return loadFromFile(FILE_JSON, clazz);
    }

    public List<T> loadFromBin(Class<T> clazz) {
        return loadFromFile(FILE_BIN, clazz);
    }

    public List<T> loadFromXml(Class<T> clazz) {
        return loadFromFile(FILE_XML, clazz);
    }

    private List<T> loadFromFile(String fileName, Class<T> clazz) {
        try {
            if (fileName.endsWith(".json")) {
                return objectMapper.readValue(new File(fileName), objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
            } else if (fileName.endsWith(".bin")) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
                    return (List<T>) in.readObject();
                }
            } else if (fileName.endsWith(".xml")) {
                return xmlMapper.readValue(new File(fileName), xmlMapper.getTypeFactory().constructCollectionType(List.class, clazz));
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean saveToFiles(List<T> objectList) {
        return saveToJson(objectList) && saveToBin(objectList) && saveToXml(objectList);
    }

    public boolean saveToJson(List<T> objectList) {
        return saveToFile(FILE_JSON, objectList);
    }

    public boolean saveToBin(List<T> objectList) {
        return saveToFile(FILE_BIN, objectList);
    }

    public boolean saveToXml(List<T> objectList) {
        return saveToFile(FILE_XML, objectList);
    }

    private boolean saveToFile(String fileName, List<T> objectList) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.writeValue(new File(fileName), objectList);
                return true;
            } else if (fileName.endsWith(".bin")) {
                try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    out.writeObject(objectList);
                    return true;
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), objectList);
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
