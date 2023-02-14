function papago(){
    // function papago(source, targer, text){

    // var papagoData = {
    //     "source" : source,
    //     "target" : targer,
    //     "text" : text
    // };

    $.ajax({
        type : "POST",
        url : "https://openapi.naver.com/v1/papago/n2mt?source=ko&target=en&text=만나서 반갑습니다." ,
        headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
                    "X-Naver-Client-Id" : "Vd906NVULAoCUQOwpQQM",
                    "X-Naver-Client-Secret" : "cM0898GJhV",
					"Access-Control-Allow-Origin" : "*"},    
        success: function(data, textStatus, xhr) {
            
            console.log(data)
            console.log(textStatus)
            console.log(xhr)
            
            
        },
        error:function(request,status,error){
            alert("친구 초대에 실패했습니다.");
            console.log(request);
            console.log(status);
            console.log(error);
        }
    });
}