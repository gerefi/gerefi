/*
 * @file tachometer.h
 *
 * @date Aug 18, 2015
 * @author Bruno Ferreira, (c) 2012-2020
 */

#pragma once


class TachometerModule : public EngineModule {
public:
    void init();
    void onFastCallback() override;
private:
    bool tachHasInit;
};