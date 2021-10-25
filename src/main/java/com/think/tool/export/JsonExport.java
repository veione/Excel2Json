package com.think.tool.export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.think.tool.data.EntityInfo;
import com.think.tool.util.FileUtils;

import java.io.File;

public class JsonExport {

    public static void Export(EntityInfo _entityInfo, String _exportPath) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        String json = gson.toJson(_entityInfo.entityMap);
        FileUtils.writeStringToFile(json, _exportPath + File.separator + _entityInfo.fileName + ".json");
        System.out.println("Save file to : " + _exportPath + File.separator + _entityInfo.fileName + ".json" + "\n");
    }
}
