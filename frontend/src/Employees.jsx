import { Link } from 'react-router-dom';
import styles from './Employees.module.css';
const Employees = ({data}) => {
    return ( 
        <div className={styles.employees}>
            {
                data.map((employee)=>(
                    <Link to={`/list/${employee.id}`} key={employee.id}>
                    <div className="employeePreview" key={employee.id}>
                        <h2>{employee.name} {employee.surname}</h2>    
                            <img className='img' src={'data:image/jpeg;base64,' + employee.photo.photo}/>
                    </div>
                    </Link>
                ))
            }
        </div>
     );
}
 
export default Employees;