import React, {useState, useEffect} from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/main/Main.scss';
import {NavLink} from "react-router-dom";
import Admin from "./Admin";

export default function Main() {
    const [mainList, setMainList] = useState([]);

    const fetchList = async () => {
        try {
            const response = await fetch('/api', {
                method: 'get',
                headers: {
                    'Accept': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`${response.status} ${response.statusText}`);
            }

            const json = await response.json();
            if (json.result !== 'success') {
                throw new Error(`${json.result} ${json.message}`);
            }
            setMainList(json.data);
            console.log(mainList);
            console.log(setMainList);
        } catch (err) {
            console.error(err);
        }
    }

    useEffect(()=>{
        fetchList();
    }, []);

    return (
        <MySiteLayout>
            <div className={styles.siteintroduction }>
            <img id="profile" src="" />
            
                <Admin no={mainList.no}
                   title={mainList.title}
                   welcome={mainList.welcome}
                   profile={mainList.profile}
                   description={mainList.description}        />

            </div>
        </MySiteLayout>
    );
}