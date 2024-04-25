import { Link } from "react-router-dom";
import Employees from "./Employees";
import useFetch from "./useFetch";

const ListOfEmployees = () => {
    const {data:employees, isPending, error} = useFetch('http://localhost:8080/api/v1/employee');
    return ( 
        
        <div className="list-of-employees">
            {!error && <Link to='/newEmployee'>Create new employee</Link>}
            {isPending && <div>loading...</div>}
            {employees && <Employees data={employees}></Employees>}
            {error && <div>Error</div>}
        </div>
     );
}
 
export default ListOfEmployees;