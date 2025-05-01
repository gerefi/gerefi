package com.gerefi.tune;

import com.opensr5.ini.IniFileModel;
import com.opensr5.ini.IniFileModelImpl;
import com.gerefi.LocalIniFileProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadCurrentIniTest {
    @Test
    public void test() {
        IniFileModel ini = IniFileModelImpl.readIniFile("../" + LocalIniFileProvider.INI_FILE_FOR_SIMULATOR);
        Assertions.assertNotNull(ini);
    }
}
