package Repository;

import Models.Employee;

public interface IEmployeeRepository {
    void save(Employee employee);
    Employee findById(int id);
    void update(Employee employee);
    void delete(Employee employee);
}