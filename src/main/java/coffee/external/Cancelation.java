package coffee.external;

public class Cancelation {

    private Long cancelId;
    private Long requestId;
    private String status;
    private String cancelTime;

    public Long getCancelId() {
        return cancelId;
    }
    public void setCancelId(Long cancelId) {
        this.cancelId = cancelId;
    }
    public Long getRequestId() {
        return requestId;
    }
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCancelTime() {
        return cancelTime;
    }
    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

}
