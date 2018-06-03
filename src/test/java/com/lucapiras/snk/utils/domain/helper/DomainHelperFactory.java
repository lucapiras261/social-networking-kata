package com.lucapiras.snk.utils.domain.helper;

/**
 *
 * @author Luca Piras
 */
public abstract class DomainHelperFactory {
    
    public static IDomainHelper getRepositoryHelper() {
        return new DomainHelper();
    }
}