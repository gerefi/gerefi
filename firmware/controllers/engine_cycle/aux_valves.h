/*
 * @file aux_valves.h
 *
 * @date Nov 25, 2017
 * @author Bruno Ferreira, (c) 2012-2020
 */

#pragma once

void initAuxValves();
void recalculateAuxValveTiming();
void auxPlainPinTurnOn(AuxActor *current);
