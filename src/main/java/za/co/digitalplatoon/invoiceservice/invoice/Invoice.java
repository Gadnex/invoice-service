package za.co.digitalplatoon.invoiceservice.invoice;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "Invoice.findAll",
            query = "SELECT i FROM Invoice AS i ORDER BY i.client"
    )
})
@JsonPropertyOrder({
    "id",
    "client",
    "vatRate",
    "invoiceDate",
    "lineItems",
    "subTotal",
    "vat",
    "total"
})
@Data
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client;

    private Long vatRate;

    @Temporal(TemporalType.DATE)
    private Date invoiceDate = new Date();

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LineItem> lineItems;

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (LineItem lineItem : lineItems) {
            subTotal = subTotal.add(lineItem.getLineItemTotal());
        }
        return subTotal;
    }

    public BigDecimal getVat() {
        BigDecimal subTotal = getSubTotal();
        BigDecimal vat = new BigDecimal(vatRate).divide(new BigDecimal(100));
        return subTotal.multiply(vat).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return getSubTotal().add(getVat()).setScale(2, RoundingMode.HALF_UP);
    }

}
