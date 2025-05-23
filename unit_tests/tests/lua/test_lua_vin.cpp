/*
 * test_lua_vin.cpp
 *
 */

#include "pch.h"
#include "gerefi_lua.h"
#include "lua_lib.h"


TEST(LuaVin, Test) {
	EngineTestHelper eth(engine_type_e::TEST_CRANK_ENGINE);

	strcpy(engineConfiguration->vinNumber, "GM123");

	const char* realdata = R"(

	function testFunc()
		return vin(0)
	end)";

	EXPECT_NEAR_M3(testLuaReturnsNumberOrNil(realdata).value_or(0), 0x47);
}
