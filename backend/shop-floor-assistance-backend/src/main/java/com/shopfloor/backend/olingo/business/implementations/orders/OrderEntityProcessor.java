package com.shopfloor.backend.olingo.business.implementations.orders;

import com.shopfloor.backend.database.objects.OrderDBO;
import com.shopfloor.backend.olingo.business.generics.processors.ODataEntityProcessor;
import com.shopfloor.backend.olingo.business.generics.projections.ODataProjectionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityProcessor extends ODataEntityProcessor<OrderDBO> {

    @Autowired
    public OrderEntityProcessor(JpaSpecificationExecutor<OrderDBO> repository, OrderODataMapper mapper) {
        super(repository, new ODataProjectionBuilder<OrderDBO>(mapper));
    }
}
