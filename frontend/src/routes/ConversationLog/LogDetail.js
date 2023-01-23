import React from 'react';

function LogDetail({match}) {
    const {room_no} = match.params

    // fetch 방 상세 정보 불러오기
    
    return (
        <>
        <h5>LogDetail</h5>
        {room_no}
        </>
    )
}

export default LogDetail;