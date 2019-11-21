package in.hocg.payment.core.service;


import in.hocg.payment.core.PaymentService;
import in.hocg.payment.core.storage.AliPayConfigStorage;

public class AliPayPaymentService extends PaymentService<AliPayConfigStorage> {
    
    public AliPayPaymentService(AliPayConfigStorage configStorage) {
        super(configStorage);
    }
    
    
}
