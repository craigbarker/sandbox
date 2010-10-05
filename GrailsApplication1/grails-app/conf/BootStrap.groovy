import org.joda.time.*
import org.sgodden.CustomerOrder

class BootStrap {

    def init = { servletContext ->
        new CustomerOrder(
            orderNumber: "123456",
            bookingDate: new LocalDate()
        ).save()
    }
    def destroy = {
    }
}
