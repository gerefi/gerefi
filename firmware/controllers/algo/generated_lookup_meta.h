#include "efi_quote.h"

#pragma once

#define META_GENERATED_NAME gerefi_generated_
#define META_GENERATED_EXT .h

// todo: sad technical debt: failing to define SHORT_BOARD_NAME for unit_tests and concatenate specifically for mac os?!
#if defined(SHORT_BOARD_NAME)

#define META_GENERATED_H_FILENAME QUOTE(META_GENERATED_NAME SHORT_BOARD_NAME META_GENERATED_EXT)
#include META_GENERATED_H_FILENAME

#else

#include "gerefi_generated_f407-discovery.h"

#endif
