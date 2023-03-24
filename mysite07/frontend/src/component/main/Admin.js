import React from 'react';
import styles from '../../assets/scss/component/main/Main.scss';

const Admin = ({mainList}) => {
    return (
        <div className={styles.siteintroduction }/>
        // {
        //     mainList.map(main => <Admin
        //                          no={main.no}
        //                          title={main.title}
        //                          welcome={main.welcome}
        //                          profile={main.profile}
        //                          description={main.description}
        //                          />)
        // }
        
    );
};

export default Admin;
