import React, {useState, useEffect} from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/main/Main.scss';
import Admin from "./Admin";

const index = () => {
    const [mainList, setMainList] = useState([]);

    const fetchList = async () => {
        try {
            const response = await fetch('/api/main', {
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
            console.log(response);

        } catch (err) {
            console.error(err.message);
        }
    }

    useEffect(()=>{
        fetchList();
    }, []);

    return (
        <MySiteLayout>
            <div className={styles.siteintroduction }>
           <span style={{}}/>
           <h2>{mainList.welcome}</h2>
           <p>
                {mainList.description}
                <br/>
                <a href=''>방명록</a>에 글 남기기<br/>
             

           </p>
           </div>
        </MySiteLayout>
    );
}


export default index;