import React from 'react';
import {Link} from "react-router-dom";

const bookTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name + " " + props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)} href={"/books"}>Delete</a> <nbsp/>
                <a title={"Mark as Taken"} className={"btn btn-primary"} href={"/books"}
                   onClick={() => props.onMarkAsTaken(props.term.id)}>Mark as Taken</a> <nbsp/>
                <Link to={`/books/edit/${props.term.id}`} title={"Edit"}
                      className={"btn btn-secondary"}
                      onClick={() => props.onEdit(props.term.id)}>Edit</Link>
            </td>
        </tr>
    );
}

export default bookTerm;