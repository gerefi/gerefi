#pragma once

#include "can_sensor.h"

#include "wideband_state_generated.h"

class AemXSeriesWideband : public CanSensorBase, public wideband_state_s {
public:
	AemXSeriesWideband(uint8_t sensorIndex, SensorType type);

	bool acceptFrame(const CANRxFrame& frame) const override final;

	void refreshState(void);

protected:
	// Dispatches to one of the three decoders below
	void decodeFrame(const CANRxFrame& frame, efitick_t nowNt) override;

	// Decode an actual AEM controller, or a gerEFI controller sending AEM format
	bool decodeAemXSeries(const CANRxFrame& frame, efitick_t nowNt);

	// Decode gerEFI custom format
	void decodeGerefiStandard(const CANRxFrame& frame, efitick_t nowNt);
	void decodeGerefiDiag(const CANRxFrame& frame);

private:
	const uint8_t m_sensorIndex;
};
