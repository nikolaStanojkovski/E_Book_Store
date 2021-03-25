import React from 'react';
import {useHistory} from 'react-router-dom';

const AddBook = (props) => {

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
        e.preventDefault();
        const name = formData.name;
        const authorId = formData.authorId;
        const category = formData.category;
        const availableCopies = formData.availableCopies;

        props.addBook(name, category, authorId, availableCopies);
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
                                   required
                                   placeholder="Enter book name"
                                   onChange={handleChange}/>
                        </div>

                        <div className="form-group">
                            <label>Category</label>
                            <select onChange={handleChange} name="category" className="form-control">
                                {props.categories.map((term) =>
                                    <option value={term}>{term}</option>
                                )}
                            </select>
                        </div>

                        <div className="form-group">
                            <label>Author</label>
                            <select onChange={handleChange} name="authorId" className="form-control">
                                {props.authors.map((term) =>
                                    <option value={term.id}>{term.name + " " + term.surname}</option>
                                )}
                            </select>
                        </div>

                        <div className="form-group">
                            <label htmlFor="availableCopies">Available Copies</label>
                            <input type="number"
                                   className="form-control"
                                   id="availableCopies"
                                   name="availableCopies"
                                   placeholder="Enter the number of available copies"
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

export default AddBook;