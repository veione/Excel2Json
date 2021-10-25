package com.think.tool.procress.sheet;

import com.think.tool.data.EntityInfo;
import com.think.tool.procress.ProcessSharedData;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.HashMap;

public class SheetProcessBasic {

    protected ProcessSharedData mSharedData;


    public void SetSharedData(ProcessSharedData _data) {
        mSharedData = _data;
    }

    public void StartProcess(HSSFSheet _sheet, String _fileName) {
    }

    protected EntityInfo GetOrCreateEntityInfoByName(String _name) {

        for (EntityInfo info : mSharedData.allEntityInfoList) {
            if (info.fileName.equals(_name)) {
                return info;
            }
        }

        EntityInfo newInfo = new EntityInfo();
        newInfo.fileName = _name;
        newInfo.entityMap = new HashMap<>();
        mSharedData.allEntityInfoList.add(newInfo);

        return newInfo;
    }
}
