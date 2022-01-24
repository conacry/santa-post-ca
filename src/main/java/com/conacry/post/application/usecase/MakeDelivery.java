package com.conacry.post.application.usecase;

import com.conacry.post.application.model.DeliveryOrderInfo;
import com.conacry.post.application.model.ElfInfo;

import javax.annotation.Nullable;
import java.util.List;

public interface MakeDelivery {

    List<DeliveryOrderInfo> execute(@Nullable ElfInfo elfInfo);
}
