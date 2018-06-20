package za.co.digitalplatoon.invoiceservice.invoice;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "invoices", produces = {MediaType.APPLICATION_JSON_VALUE})
@Slf4j
public class InvoiceController {

    @Autowired
    private EntityManager em;

    @PostMapping
    @Transactional
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        log.debug("Invoice: {}", invoice);
        em.persist(invoice);
        em.flush();
        return invoice;
    }

    @GetMapping
    public List<Invoice> viewAllInvoices() {
        TypedQuery<Invoice> query = em.createNamedQuery("Invoice.findAll", Invoice.class);
        return query.getResultList();
    }

    @GetMapping(path = "{invoiceId}")
    public Invoice viewInvoice(@PathVariable Long invoiceId) {
        Invoice invoice = em.find(Invoice.class, invoiceId);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The invoiceId is invalid");
        }
        return invoice;
    }

}
