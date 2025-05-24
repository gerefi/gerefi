# Warnings-as-errors...
GEREFI_OPT = -Werror
# some compilers seem to have this off by default?
GEREFI_OPT += -Werror=stringop-truncation

ifneq ($(ALLOW_SHADOW),yes)
     GEREFI_OPT += -Werror=shadow
endif

# ...except these few
GEREFI_OPT += -Wno-error=sign-compare
GEREFI_OPT += -Wno-error=overloaded-virtual
GEREFI_OPT += -Wno-error=unused-parameter
