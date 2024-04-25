import { useParams,useNavigate } from "react-router-dom";
import useFetch from "./useFetch";

const SpecificEmployee = () => {
    const {id} = useParams();
    const {data:employee,isPending,error} = useFetch('http://localhost:8080/api/v1/employee/'+id);
    const navigation = useNavigate();
    const handleClickFire=()=>{
        fetch('http://localhost:8080/api/v1/employee/fire/'+id,
        {method: 'PUT'}
        ).then(()=>{
            navigation('/list');
        })
    };
    return ( 
        <div className="specific-employee">
           {
           employee && (<><img className='img' src={'data:image/jpeg;base64,' + employee.photo.photo}/>
            <h1>Full name: {employee.name} {employee.surname}</h1>
            <p>Email: {employee.email}</p>
            <p>Date of birth: {employee.dateOfBirth}</p>
            <p>Date of hiring: {employee.dateOfHiring}</p>
            {employee.dateOfFiring && <p>Date of firing: {employee.dateOfFiring}</p>}
            {!employee.dateOfFiring && (<><p>Currently working</p> <button onClick={handleClickFire}>Fire this employee</button></>)}
            </>)}
        </div>
     );
}
 
export default SpecificEmployee;