import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/User.scss';

export default function SignIn() {
    return (
        <MySiteLayout>
            <div className={styles.SignIn}>
                <h2>Sign In</h2>
            </div>
        </MySiteLayout>
    );
}