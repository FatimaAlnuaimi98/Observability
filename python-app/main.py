from fastapi import FastAPI
from pydantic import BaseModel


class Item(BaseModel):
    bread: str
    price: float

app = FastAPI()

# In-memory list to store breads
breads = [
    Item(bread="Potato Bread", price=2.5),
    Item(bread="Sourdough", price=3.0),
    Item(bread="Baguette", price=2.0)
]

@app.get("/bakery")
async def bakery():
    return {"message": "Hello, please visit /bakery to checkout the types of bread available."}     

@app.get("/breads", response_model=list[Item])
async def get_breads():
    return breads

@app.post("/add_bread")
async def add_bread(item: Item):
    try :
        breads.append(item)
        return {"added new bread": item.model_dump()}
    except Exception as e:
        return {"error": str(e)}