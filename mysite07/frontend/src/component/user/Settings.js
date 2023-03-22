import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/User.scss';

export default function Settings() {
    return (
        <MySiteLayout>
            <div className={styles.SignIn}>
            <fieldset>
                <label class="block-label" for="name">이름</label>
                <input id="name" name="name" type="text" />

                <label class="block-label" for="email">이메일</label>
                <h4>dooly@naver.com</h4>

                <label class="block-label">비밀번호</label>
                <input name="password" type="password" value=""></input>

                <input type="submit" value="수정하기"></input>
            </fieldset>
            </div>
        </MySiteLayout>
    );
}