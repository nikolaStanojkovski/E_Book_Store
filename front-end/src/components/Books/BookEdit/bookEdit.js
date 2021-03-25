import React from 'react';
import {useHistory} from 'react-router-dom';

const EditBook = (props) => {

    const History = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "NOVEL",
        authorId: 0,
        availableCopies: 0
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== "NOVEL" ? formData.category : props.book.category;
        const authorId = formData.authorId !== 0 ? formData.authorId : props.book.author.id;
        const availableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;

        props.editBook(props.book.id, name, category, authorId, availableCopies);
        History.push("/books");
    }

    return (
        <div className="container">
            <div>
                <div className="col-md-5">
                    <br/>
                    <form onSubmit={onFormSubmit}>
                        <div className="form-group">
                            <label htmlFor="name">Name</label>
                            <input type="text"
                                   className="form-control"
                                   id="name"
                                   name="name"
                                   placeholder={props.book.name}
                                   onChange={handleChange}/>
                        </div>

                        <div className="form-group">
                            <label>Category</label>
                            <select onChange={handleChange} name="category" className="form-control">
                                {props.categories.map((term) => {
                                    if (props.book.category !== undefined &&
                                        props.book.category === term)
                                        return <option selected={props.book.category} value={term}>{term}</option>
                                    else return <option value={term}>{term}</option>
                                })}
                            </select>
                        </div>

                        <div className="form-group">
                            <label>Author</label>
                            <select onChange={handleChange} name="authorId" className="form-control">
                                {props.authors.map((term) => {
                                    if (props.book.author !== undefined &&
                                        props.book.author.id === term.id)
                                        return <option selected={props.book.author.id}
                                                       value={term.id}>{term.name + " " + term.surname}</option>
                                    else return <option value={term.id}>{term.name + " " + term.surname}</option>
                                })}
                            </select>
                        </div>

                        <div className="form-group">
                            <label htmlFor="availableCopies">Available Copies</label>
                            <input type="number"
                                   className="form-control"
                                   id="availableCopies"
                                   name="availableCopies"
                                   placeholder={props.book.availableCopies}
                                   onChange={handleChange}/>
                        </div>

                        <br/>
                        <button id="submit" type="submit" className="btn btn-primary">Submit</button>

                        <br/>
                        <a type="button" className="btn btn-link" href="/books">Back to books list</a>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default EditBook;