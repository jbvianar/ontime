package br.com.uol.pagseguro.api.direct.preapproval;

import br.com.uol.pagseguro.api.common.domain.DateRange;

/**
 * Interface for Direct Pre Approval By Date Interval List
 *
 * @author PagSeguro Internet Ltda.
 */
public interface DirectPreApprovalByDateIntervalList {

    /**
     * Number of the page desired to see the result
     *
     * @return Page number
     */
    Integer getPage();

    /**
     * Max number of registers by page
     *
     * @return Max Page Results
     */
    Integer getMaxPageResults();

    /**
     * Date Range of registers
     *
     * @return Date Range
     */
    DateRange getDateRange();

    /**
     * Status of the direct pre approval
     *
     * @return Direct Pre Approval Status
     */
    String getStatus();

    /**
     * Direct Pre Approval Code
     *
     * @return Direct Pre Approval Code
     */
    String getCode();

    /**
     * Sender Email
     *
     * @return Sender Email
     */
    String getSenderEmail();
}