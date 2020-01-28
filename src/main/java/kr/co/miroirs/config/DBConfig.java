package kr.co.miroirs.config;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

public class DBConfig implements TransactionManagementConfigurer {

    public PlatformTransactionManager annotationDrivenTransactionManager() {
        // TODO Auto-generated method stub
        return null;
    }

}
