import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/User.scss';

export default function SignUp() {
    return (
        <MySiteLayout>
            <div className={styles.SignUp}>
            <fieldset>
            <label class="block-label" for="name">이름</label><br/>
                <input path="name"/><br/>
            <label class="block-label" for="email">이메일</label><br/>
                <input path="email"/><br/>
            <label class="block-label" for="password">비밀번호</label><br/>
                <input type="password" id="password" class="account"></input>

                <fieldset>
                    <legend>성별</legend>
                        <input type='radio' path="gender" value="female" />여 
                        <input type='radio' path="gender" value="male"/>남
                </fieldset>
            <fieldset>
				<legend>약관동의</legend>
					<input type="checkbox" 
                        checked='y'
                        onChange={e => {
                        callbackChangetaskDone(no, e.target.checked ? "Y":"N");
                }} />
					<label>서비스 약관에 동의합니다.</label>
            <input type="submit" value="가입하기"></input>
            </fieldset>
            </fieldset>
            </div>
        </MySiteLayout>
    );
}