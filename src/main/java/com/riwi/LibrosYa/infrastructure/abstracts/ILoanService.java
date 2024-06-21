package com.riwi.LibrosYa.infrastructure.abstracts;

import com.riwi.LibrosYa.api.dto.request.LoanRequest;
import com.riwi.LibrosYa.api.dto.response.LoanResponse;

public interface ILoanService extends BaseService<LoanRequest, LoanResponse, Long> {
    
}
