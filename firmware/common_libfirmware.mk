# Configure libfirmware Paths/Includes
GEREFI_LIB = $(PROJECT_DIR)/libfirmware
include $(GEREFI_LIB)/util/util.mk
include $(GEREFI_LIB)/pt2001/pt2001.mk
include $(GEREFI_LIB)/sent/sent.mk

INCDIR += $(GEREFI_LIB)/sent/include
