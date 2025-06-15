package tung.example;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing square");
    }

    public void resize() {
        System.out.println("Resizing square");
    }
}
