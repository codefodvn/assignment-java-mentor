package src.factory;

import lombok.Getter;
import lombok.Setter;
import src.model.Transaction;
import src.service.*;
import src.service.impl.*;

import java.util.ArrayList;
import java.util.List;
public class ServiceManager {
    private static final List<Transaction> transactionList = new ArrayList<>();
    @Getter
    private static final OPTService optService = OPTServiceImpl.getInstance();
    @Getter
    private static final FraudDetectionService fraudDetectionService = FraudDetectionServiceImpl.createInstance(transactionList);
    @Getter
    private static final TransactionService transactionService = TransactionServiceImpl.getInstance(optService,transactionList);
    @Getter
    private static final RefundService refundService = RefundServiceImpl.getInstance(transactionList);
    @Getter
    private static final UserService userService = UserServiceImpl.getInstance();
    @Getter
    private static final PaymentService paymentService = PaymentServiceImpl.getInstance();

}
