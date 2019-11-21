package in.hocg.payment.core.service;


import in.hocg.payment.core.request.PaymentService;
import in.hocg.payment.core.storage.AliPayConfigStorage;

public class AliPayPaymentService extends PaymentService<AliPayConfigStorage> {
    
    public AliPayPaymentService(AliPayConfigStorage configStorage) {
        super(configStorage);
    }
    
    
}
