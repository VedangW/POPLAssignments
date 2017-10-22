#include <iostream>
#include <time.h>
using namespace std;

// Size of arrays
int n = 60;

// Template to copy from source array to destination array
template<int from, int to>
class Unroller {
public:
    static inline void copyArray(int *source, int *destination) {
        source[from] = destination[from];
        Unroller<from+1,to>::copyArray(source, destination);
    }
};

// Terminal case
template<int from>
class Unroller<from, from> {
public:
    static inline void copyArray(int *source, int *destination) {
        source[from] = destination[from];
    }
};

void copyRecursive(int *a, int *b, int N, int j){
	if (j == 0){
		Unroller<0, N-1>::copyArray(a, b);
	}
	else
}

//  N = unrolling factor
void copy(int *a, int *b, int N)
{
	if (j == 0){
		Unroller<0, N-1>::copyArray(a, b);
	}
}

void loop_over_copy(int *a, int *b)
{
	copy(a, b, 7);
	/*
	for(int i = 0; i <= 10; i++){
		clock_t tStart = clock();

		int N = (int) pow(2, i);
		copy(a, b, N);

		double timeNow = (double) (clock() - tStart);
		cout << "Unroll factor = " << N << endl;
		cout << "Time taken = " << timeNow << endl;
		cout << "\n";
	}
	*/
}

int main()
{
	int a[n], b[n];
	int i;
	for(i = 0; i < n; i++){
		a[i] = i;
		b[i] = 0;
	}

	cout << "Initially b is: " << endl;
	for (i = 0; i < n; i++)
		cout << b[i] << " ";
	cout << "\n";

	loop_over_copy(a, b);

	cout << "Finally b is: " << endl;
	for (i = 0; i < n; i++)
		cout << b[i] << " ";
	cout << "\n";

	return 0;
}