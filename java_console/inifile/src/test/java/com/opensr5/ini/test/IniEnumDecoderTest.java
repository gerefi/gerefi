package com.opensr5.ini.test;

import com.opensr5.ini.field.EnumIniField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IniEnumDecoderTest {
    @Test
    public void testKeyValueSyntax() {
        String line = "engineType = bits, S32, 0, [0:6], 0=\"DEFAULT_FRANKENSO\",97=\"ALPHAX_8CHAN_SBC\",24=\"BMW_M73_M\",50=\"BMW_M73_MRE\",51=\"BMW_M73_MRE_SLAVE\",35=\"CAMARO_4\",65=\"CITROEN_TU3JP\",38=\"DISCOVERY_PDM\",2=\"DODGE_NEON_1995\",46=\"DODGE_NEON_2003_CRANK\",64=\"DODGE_RAM\",61=\"EEPROM_BENCH_ENGINE\",58=\"ETB_BENCH_ENGINE\",3=\"FORD_ASPIRE_1996\",11=\"FORD_COYOTE\",14=\"FORD_ESCORT_GT\",7=\"FORD_INLINE_6_1995\",40=\"FRANKENSO_BMW_M73_F\",47=\"FRANKENSO_MAZDA_MIATA_2003\",56=\"FRANKENSO_MAZDA_MIATA_NA8\",41=\"FRANKENSO_MIATA_NA6_MAP\",49=\"FRANKENSO_QA_ENGINE\",8=\"GY6_139QMB\",33=\"HELLEN72_ETB\",84=\"HELLEN_121_NISSAN_4_CYL\",72=\"HELLEN_121_NISSAN_6_CYL\",85=\"HELLEN_121_NISSAN_8_CYL\",87=\"HELLEN_121_NISSAN_ALMERA_N16\",71=\"HELLEN_121_VAG_4_CYL\",77=\"HELLEN_121_VAG_5_CYL\",81=\"HELLEN_121_VAG_8_CYL\",78=\"HELLEN_121_VAG_V6_CYL\",79=\"HELLEN_121_VAG_VR6_CYL\",36=\"HELLEN_128_MERCEDES_4_CYL\",88=\"HELLEN_128_MERCEDES_6_CYL\",89=\"HELLEN_128_MERCEDES_8_CYL\",75=\"HELLEN_134_BMW\",82=\"HELLEN_154_HYUNDAI_COUPE_BK1\",95=\"HELLEN_154_HYUNDAI_COUPE_BK2\",76=\"HELLEN_154_VAG\",73=\"HELLEN_55_BMW\",74=\"HELLEN_88_BMW\",34=\"HELLEN_NA6\",10=\"HELLEN_NA8_96\",80=\"HELLEN_NA94\",83=\"HELLEN_NB1\",94=\"HELLEN_NB1_36\",69=\"HELLEN_NB2\",86=\"HELLEN_NB2_36\",43=\"HONDA_600\",60=\"L9779_BENCH_ENGINE\",55=\"MAZDA_MIATA_2003_BOARD_TEST\",54=\"MAZDA_MIATA_2003_NA_RAIL\",21=\"MIATA_1996\",1=\"MIATA_PROTEUS_TCU\",99=\"MINIMAL_PINS\",12=\"MITSUBISHI_3A92\",16=\"MITSUBISHI_4G93\",31=\"MRE_BOARD_NEW_TEST\",23=\"MRE_BODY_CONTROL\",68=\"MRE_M111\",20=\"MRE_MIATA_94_MAP\",66=\"MRE_MIATA_NA6_MAP\",4=\"MRE_SECONDARY_CAN\",37=\"MRE_SUBARU_EJ18\",62=\"MRE_VW_B6\",5=\"NISSAN_PRIMERA\",30=\"PROTEUS_ANALOG_PWM_TEST\",63=\"PROTEUS_BMW_M73\",9=\"PROTEUS_E65_6H_MAN_IN_THE_MIDDLE\",27=\"PROTEUS_GM_LS_4\",6=\"PROTEUS_HARLEY\",90=\"PROTEUS_HONDA_K\",91=\"PROTEUS_HONDA_OBD2A\",104=\"PROTEUS_HYUNDAI_PB\",25=\"PROTEUS_LUA_DEMO\",17=\"PROTEUS_M111\",67=\"PROTEUS_MIATA_NB2\",93=\"PROTEUS_N73\",103=\"PROTEUS_NISSAN_VQ35\",42=\"PROTEUS_QC_TEST_BOARD\",98=\"PROTEUS_SBC\",92=\"PROTEUS_VAG_80_18T\",39=\"PROTEUS_VW_B6\",29=\"SACHS\",70=\"SUBARUEG33_DEFAULTS\",22=\"SUBARU_2003_WRX\",100=\"TEST_100\",101=\"TEST_101\",102=\"TEST_102\",18=\"TEST_33816\",28=\"TEST_CRANK_ENGINE\",48=\"TEST_DC_WASTEGATE_DISCOVERY\",26=\"TEST_ENGINE\",45=\"TEST_ENGINE_VVT\",52=\"TEST_ISSUE_366_BOTH\",53=\"TEST_ISSUE_366_RISE\",19=\"TEST_ROTARY\",59=\"TLE8888_BENCH_ENGINE\",13=\"TOYOTA_1NZ_FE\",44=\"TOYOTA_2JZ_GTE_VVTi\",15=\"UNUSED_15\",57=\"UNUSED_57\",32=\"VW_ABA\",96=\"WASTEGATE_PROTEUS_TEST\"";

        EnumIniField.EnumKeyValueMap m = EnumIniField.EnumKeyValueMap.valueOf(line, null);

        assertEquals(105, m.size());
        assertEquals("DEFAULT_FRANKENSO", m.get(0));
        assertEquals("MIATA_PROTEUS_TCU", m.get(1));

        assertEquals(0, m.indexOf("DEFAULT_FRANKENSO"));
        assertEquals(24, m.indexOf("BMW_M73_M"));
    }

    @Test
    public void testArraySyntax() {
        String line = "engineType = bits, S32, 0, [0:6], \"DEFAULT_FRANKENSO\",\"ALPHAX_8CHAN_SBC\"";
        EnumIniField.EnumKeyValueMap m = EnumIniField.EnumKeyValueMap.valueOf(line, null);
        assertEquals(2, m.size());

        assertEquals("DEFAULT_FRANKENSO", m.get(0));
        assertEquals("ALPHAX_8CHAN_SBC", m.get(1));

        assertEquals(0, m.indexOf("DEFAULT_FRANKENSO"));
    }
}
