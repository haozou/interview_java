/**
 * How hashtable works:
 * Essentially, a hash function is just a function that takes things from one space (say strings of arbitrary length) and maps them to a space useful for indexing (unsigned integers, say).

If you only have a small space of things to hash, you might get away with just interpreting those things as integers, and you're done (e.g. 4 byte strings)

Usually, though, you've got a much larger space. If the space of things you allow as keys is bigger than the space of things you are using to index (your uint32's or whatever) then you can't possibly have a unique value for each one. When two or more things hash to the same result, you'll have to handle the redundancy in an appropriate way (this is usually referred to as a collision, and how you handle it or don't will depend a bit on what you are using the hash for).

This implies you want it to be unlikely to have the same result, and you probably also would really like the hash function to be fast.

Balancing these two properties (and a few others) has kept many people busy!

In practice you usually should be able to find a function that is known to work well for your application and use that.

Now to make this work as a hashtable: Imagine you didn't care about memory usage. Then you can create an array as long as your indexing set (all uint32's, for example). As you add something to the table, you hash it's key and look at the array at that index. If there is nothing there, you put your value there. If there is already something there, you add this new entry to a list of things at that address, along with enough information (your original key, or something clever) to find which entry actually belongs to which key.

So as you go a long, every entry in your hashtable (the array) is either empty, or contains one entry, or a list of entries. Retrieving is a simple as indexing into the array, and either returning the value, or walking the list of values and returning the right one.

Of course in practice you typically can't do this, it wastes too much memory. So you do everything based on a sparse array (where the only entries are the ones you actually use, everything else is implicitly null).

There are lots of schemes and tricks to make this work better, but that's the basics.
 * @author Hao
 *
 */
public class HashTable {

}
