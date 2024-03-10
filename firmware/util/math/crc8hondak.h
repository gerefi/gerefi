/*
 * crc8hondak.h
 *
 * Code generated by universal_crc by Danjel McGougan
 *
 * matches CRC8_EBU with 0xFF OutXor
 *
 * CRC parameters used:
 *   bits:       8
 *   poly:       0x1d
 *   init:       0xff
 *   xor:        0xff
 *   reverse:    true
 *   non-direct: false
 *
 * CRC of the string "123456789" is 0x68
 *
 * keywords: honda checksum
 */

uint8_t crc_hondak_calc(const uint8_t *data, size_t len);