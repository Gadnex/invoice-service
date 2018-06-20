package za.co.digitalplatoon.invoiceservice.invoice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@JsonPropertyOrder({
    "id",
    "quantity",
    "description",
    "unitPrice",
    "lineItemTotal"
})
@Data
public class LineItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    private String description;

    private BigDecimal unitPrice;

    @ManyToOne
    @JsonBackReference
    private Invoice invoice;

    public BigDecimal getLineItemTotal() {
        return unitPrice.multiply(new BigDecimal(quantity));
    }

}
