package com.conacry.snowone.application.usecase;

import com.conacry.snowone.application.model.DeliveryOrderInfo;
import com.conacry.snowone.application.model.ElfInfo;

import javax.annotation.Nullable;
import java.util.List;

public interface MakeDelivery {

    List<DeliveryOrderInfo> execute(@Nullable ElfInfo elfInfo);
}
