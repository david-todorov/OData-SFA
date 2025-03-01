package com.shopfloor.backend.olingo.business.processors.products;

import com.shopfloor.backend.database.objects.ProductDBO;
import com.shopfloor.backend.olingo.business.processors.generics.ODataPrimitiveProcessor;
import com.shopfloor.backend.olingo.business.processors.generics.utils.projections.ODataProjectionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public class ProductPrimitiveProcessor extends ODataPrimitiveProcessor<ProductDBO> {

    @Autowired
    public ProductPrimitiveProcessor(JpaSpecificationExecutor<ProductDBO> repository, ProductODataMapper mapper) {
        super(repository, new ODataProjectionBuilder<ProductDBO>(mapper));
    }
}
