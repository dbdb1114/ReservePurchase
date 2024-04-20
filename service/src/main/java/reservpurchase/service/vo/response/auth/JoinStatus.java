package reservpurchase.service.vo.response.auth;

public enum JoinStatus{
    SU("Success"),
    DE("Duplicate Email"),
    DP("Duplicate Phone"),
    FA("Fail");

    JoinStatus(String message){
        this.responseVo.setResponseCode(this.name());
        this.responseVo.setResponseMessage(message);
    }

    public ResponseVo responseVo = new ResponseVo();
}
