import React from "react";

const countries = (props) => {
    return (
        <div className={"container"}>
            <div>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Continent</th>
                            </tr>
                        </thead>
                        <tbody>
                        {props.countries.map((term) => {
                            return (
                                <tr>
                                    <td>{term.name}</td>
                                    <td>{term.continent}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default countries;