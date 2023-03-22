import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/User.scss';

export default function SignIn() {
    return (
        <MySiteLayout>
            <div className={styles.SignIn}>
                <fieldset>
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text"/>

					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value=""/>

                    <input type="submit" value="로그인"></input>
                </fieldset>
            </div>
        </MySiteLayout>
    );
}